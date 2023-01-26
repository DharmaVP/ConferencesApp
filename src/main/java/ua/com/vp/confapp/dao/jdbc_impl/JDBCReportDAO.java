package ua.com.vp.confapp.dao.jdbc_impl;


import ua.com.vp.confapp.dao.ReportDAO;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.Report;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.utils.DAOUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.EventQueries.SQL_COUNT_EVENTS;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.ReportQueries.*;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;


public class JDBCReportDAO extends JDBCEntityDAO implements ReportDAO {


    @Override
    public boolean create(Report report) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(report);
        Object[] params = getReportParams(report);
        try (PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SQL_ADD_REPORT, true, params)) {
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating report failed, no rows affected.");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                report.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }


    @Override
    public Optional<Report> read(Long key) throws DAOException {
        Report report = null;
        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_REPORT_BY_ID, false, key);
             ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {
                report = mapReport(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(report);
    }


    @Override
    public boolean update(Report report) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNotNull(report);
        Object[] params = getReportUpdateParams(report);

        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_REPORT, false, params);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating report failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }


    @Override
    public boolean delete(Report report) throws DAOException {
        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DELETE_REPORT, false, report.getId());) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting report failed, no rows affected.");
            } else {
                report.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public List<Report> getAll(QueryBuilder queryBuilder) throws DAOException {
        List<Report> reports = new ArrayList<>();
        String query = queryBuilder.getQuery();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_REPORTS + query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                reports.add(mapReport(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return reports;
    }

    @Override
    public int getNoOfRecords(QueryBuilder queryBuilder) throws DAOException {
        int reportNumber = 0;
        String conditionQuery = queryBuilder.getRecordQuery();
        try (PreparedStatement statement = connection.prepareStatement(SQL_COUNT_REPORTS + conditionQuery);
             ResultSet resultSet = statement.executeQuery();) {
            if (resultSet.next()) {
                reportNumber = resultSet.getInt(COUNT);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return reportNumber;
    }

    private Object[] getReportParams(Report report) {
        return new Object[]{
                report.getTopic(),
                report.getOutline(),
                report.getEvent().getId(),
                report.getSpeaker() != null ? report.getSpeaker().getId() : null,
                report.getAcceptedByModerator(),
                report.getAcceptedByModerator()
        };
    }

    private Report mapReport(ResultSet resultSet) throws SQLException {
        Report report = new Report();
        Event event = obtainEvent(resultSet);
        User speaker = obtainSpeaker(resultSet);
        report.setId(resultSet.getLong(REPORT_ID));
        report.setTopic(resultSet.getString(TOPIC));
        report.setOutline(resultSet.getString(OUTLINE));
        report.setEvent(event);
        report.setSpeaker(speaker);
        report.setAcceptedByModerator(resultSet.getBoolean(ACCEPTED_BY_MODERATOR));
        report.setAcceptedBySpeaker(resultSet.getBoolean(ACCEPTED_BY_SPEAKER));
        return report;
    }

    private User obtainSpeaker(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.getLong(USER_ID) != 0) {
            user = new User();
            user.setId(resultSet.getLong(USER_ID));
            user.setPrefix(getUserPrefix(resultSet));
            user.setFirstName(resultSet.getString(FIRST_NAME));
            user.setLastName(resultSet.getString(LAST_NAME));
            user.setJobTitle(resultSet.getString(JOB_TITLE));
            user.setOrganisation(resultSet.getString(ORGANISATION));
            user.setProfileImage(resultSet.getString(PROFILE_PICTURE));
        }
        return user;
    }

    private Event obtainEvent(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong(EVENT_ID));
        return event;
    }

    private User.Prefix getUserPrefix(ResultSet resultSet) throws SQLException {
        String userPrefix = resultSet.getString(PREFIX);
        return (userPrefix == null) ? null
                : Arrays.stream(User.Prefix.values())
                .filter(prefix -> prefix.getName().equals(userPrefix))
                .findFirst().orElseThrow();
    }

    private Object[] getReportUpdateParams(Report report) {
        return new Object[]{
                report.getTopic(),
                report.getOutline(),
                report.getSpeaker() == null ? null : report.getSpeaker().getId(),
                report.getAcceptedByModerator(),
                report.getAcceptedBySpeaker(),
                report.getId()
        };
    }
}
