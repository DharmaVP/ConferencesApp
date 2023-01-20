package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

public interface ReportService extends Service<ReportDTO> {

    List<ReportDTO> getReportsByEventId(QueryBuilder queryBuilder) throws ServiceException;
}
