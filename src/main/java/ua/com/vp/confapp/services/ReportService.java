package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

public interface ReportService extends Service<ReportDTO> {


    void create(ReportDTO reportDTO) throws ServiceException;

    void approve(Long reportId, Boolean decision, Boolean isProposed) throws ServiceException;

    void accept(Long reportId, UserDTO userDTO, Boolean decision) throws ServiceException;

    void setSpeaker(Long reportId, UserDTO speaker) throws ServiceException;

}
