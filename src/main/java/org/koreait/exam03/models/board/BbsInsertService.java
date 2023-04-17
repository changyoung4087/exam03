package org.koreait.exam03.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.exam03.controllers.boards.BbsParam;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BbsInsertService {
    private final BbsDAO bbsDao;
    public void dataInsert(BbsParam bbsParam){
        Bbs bbs = new Bbs();
        bbs.setBbsTitle(bbsParam.getBbsTitle());
        bbs.setBbsContent(bbsParam.getBbsContent());

        bbsDao.insert(bbs);
    }

    public void dataUpdate(BbsParam bbsParam){
        Bbs bbs = new Bbs();
        bbs.setId(bbsParam.getId());
        bbs.setBbsTitle(bbsParam.getBbsTitle());
        bbs.setBbsContent(bbsParam.getBbsContent());

        bbsDao.update(bbs);
    }

    public void dataDelete(BbsParam bbsParam){
        Bbs bbs = new Bbs();
        bbs.setId(bbsParam.getId());
        bbs.setBbsTitle(bbsParam.getBbsTitle());
        bbs.setBbsContent(bbsParam.getBbsContent());

        bbsDao.delete(bbs);
    }
}
