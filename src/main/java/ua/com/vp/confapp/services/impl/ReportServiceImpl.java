package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.DAOFactory;
import ua.com.vp.confapp.dao.ReportDAO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;

import java.util.List;

public class ReportServiceImpl implements ReportService {
    DAOFactory daoFactory;

    public ReportServiceImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }




    @Override
    public ReportDTO getById(String id) throws ServiceException {
        return null;
    }

    @Override
    public List<ReportDTO> getAll() throws ServiceException {
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
}
