package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.Report;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.NoSuchEntityException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static ua.com.vp.confapp.utils.MapperDTO.*;

public class ReportServiceImpl implements ReportService {
    private final Transaction<DAO> transaction;
    private final ReportDAO reportDAO;


    public ReportServiceImpl(Transaction<DAO> transaction, ReportDAO reportDAO) {
        this.transaction = transaction;
        this.reportDAO = reportDAO;
    }


    @Override
    public void create(ReportDTO reportDTO) throws ServiceException {
        Report report = convertToReport(reportDTO);
        try {
            transaction.beginNoTransaction(reportDAO);
            reportDAO.create(report);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
    }

    @Override
    public ReportDTO getById(Long id) throws ServiceException {
        Report report;
        try {
            transaction.beginNoTransaction(reportDAO);
            report = reportDAO.read(id).orElseThrow(NoSuchEntityException::new);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return convertToReportDTO(report);
    }

    @Override
    public List<ReportDTO> getAll(QueryBuilder queryBuilder) throws ServiceException {
        List<ReportDTO> reportsDTO = new ArrayList<>();
        List<Report> reports;
        try {
            transaction.beginNoTransaction(reportDAO);
            reports = reportDAO.getAll(queryBuilder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        for (Report report : reports) {
            reportsDTO.add(convertToReportDTO(report));
        }
        return reportsDTO;
    }


    @Override
    public void update(ReportDTO reportDTO) throws ServiceException {
        if (reportDTO.getSpeakerId() == null) {
            reportDTO.setAcceptedByModerator(false);
            reportDTO.setAcceptedBySpeaker(false);
        } else {
            reportDTO.setAcceptedByModerator(true);
            reportDTO.setAcceptedBySpeaker(false);
        }
        Report report = convertToReport(reportDTO);
        try {
            transaction.beginNoTransaction(reportDAO);
            reportDAO.update(report);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
    }

    @Override
    public void delete(ReportDTO reportDTO) throws ServiceException {
        Report report = convertToReport(reportDTO);

        try {
            transaction.beginNoTransaction(reportDAO);
            reportDAO.delete(report);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
    }

    @Override
    public int getNumberOfRecords(QueryBuilder queryBuilder) throws ServiceException {
        int numberOfRecords = 0;
        try {
            transaction.beginNoTransaction(reportDAO);
            numberOfRecords = reportDAO.getNoOfRecords(queryBuilder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return numberOfRecords;
    }


    @Override
    public void approve(Long reportId, Boolean decision, Boolean isProposed) throws ServiceException {
        try {
            transaction.begin(List.of(reportDAO));
            transaction.setRepeatableRead();
            Report report = reportDAO.read(reportId).orElseThrow(NoSuchEntityException::new);
            if (Boolean.TRUE.equals(decision)) {
                if (report.getSpeaker() != null) {
                    report.setAcceptedBySpeaker(true);
                    report.setAcceptedByModerator(true);
                    reportDAO.update(report);
                } else {
                    throw new DAOException("Somebody rejected user");
                }
            } else {
                if (Boolean.FALSE.equals(report.getAcceptedByModerator())) {
                    report.setSpeaker(null);
                    report.setAcceptedBySpeaker(false);
                    report.setAcceptedByModerator(false);

                    if (Boolean.TRUE.equals(isProposed)) {
                        reportDAO.delete(report);
                    } else {
                        reportDAO.update(report);
                    }
                } else {
                    throw new DAOException("Somebody accepted user");
                }
            }
            transaction.commit();
        } catch (DAOException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.cancelRepeatableRead();
            transaction.end();
        }
    }

    @Override
    public void accept(Long reportId, UserDTO userDTO, Boolean decision) throws ServiceException {
        User user = convertToUser(userDTO);
        try {
            transaction.begin(List.of(reportDAO));
            transaction.setRepeatableRead();
            Report report = reportDAO.read(reportId).orElseThrow(NoSuchEntityException::new);
            if (report.getSpeaker().equals(user)) {
                if (Boolean.TRUE.equals(decision)) {
                    report.setAcceptedBySpeaker(true);
                    reportDAO.update(report);
                } else {
                    report.setSpeaker(null);
                    report.setAcceptedByModerator(false);
                    report.setAcceptedBySpeaker(false);
                    reportDAO.update(report);
                }
            } else {
                throw new DAOException("Speaker has been changed before decision");
            }
            transaction.commit();
        } catch (DAOException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.cancelRepeatableRead();
            transaction.end();
        }
    }

    @Override
    public void setSpeaker(Long reportId, UserDTO speaker) throws ServiceException {
        User user = convertToUser(speaker);
        try {
            transaction.begin(List.of(reportDAO));
            transaction.setRepeatableRead();
            Report report = reportDAO.read(reportId).orElseThrow(NoSuchEntityException::new);
            if (report.getSpeaker() == null) {
                report.setSpeaker(user);
                report.setAcceptedBySpeaker(true);
                reportDAO.update(report);
                transaction.commit();
            } else {
                throw new DAOException("Somebody has recently took the place");
            }
        } catch (DAOException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.cancelRepeatableRead();
            transaction.end();
        }
    }
}
