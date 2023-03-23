package service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.vp.confapp.dao.ReportDAO;
import ua.com.vp.confapp.dao.Transaction;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.Report;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.impl.ReportServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    private static final Report report = new Report();
    private static final ReportDTO reportDTO = ReportDTO.builder().build();

    @Mock
    ReportDAO reportDAO;

    @Mock
    Transaction transaction;

    @Test
    void testCreate() throws Exception {
        assertNotNull(reportDAO);
        when(reportDAO.create(report)).thenReturn(true);
        ReportService reportService  = new ReportServiceImpl(transaction, reportDAO);
        reportService.create(reportDTO);
        assertNull(reportDTO.getId());
    }
}
