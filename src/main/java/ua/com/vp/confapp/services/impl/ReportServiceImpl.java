package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.entities.Report;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static ua.com.vp.confapp.utils.MapperDTO.convertToReportDTO;

public class ReportServiceImpl implements ReportService {
    private final Transaction<DAO> transaction;
    private final ReportDAO reportDAO;


    public ReportServiceImpl(Transaction<DAO> transaction, ReportDAO reportDAO) {
        this.transaction = transaction;
        this.reportDAO = reportDAO;
    }


    @Override
    public ReportDTO getById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public List<ReportDTO> getAll(QueryBuilder queryBuilder) throws ServiceException {
        return null;
    }


    @Override
    public boolean update(ReportDTO entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(ReportDTO entity) throws ServiceException {
        return false;
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
    public List<ReportDTO> getReportsByEventId(QueryBuilder queryBuilder) throws ServiceException {
        List<ReportDTO> reportsDTO = new ArrayList<>();;
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
}
