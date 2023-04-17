package org.koreait.exam03.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.exam03.models.board.BbsDAO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log
@RequiredArgsConstructor
public class LogScheduler {

    private final BbsDAO bbsDao;

    @Scheduled(cron="0 0 1 * * *")
    public void logScheduler(){
        log.info("매일 새벽 1시에 실행.");
        bbsDao.schedulerCount();
    }
}
