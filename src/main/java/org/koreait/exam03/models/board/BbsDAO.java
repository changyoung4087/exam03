package org.koreait.exam03.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BbsDAO {

    private final JdbcTemplate jdbcTemplate;

    /** 게시글 리스트 */
    public List<Bbs> bbsList(){
        try{
            String sql = "SELECT ID, BBSTITLE, BBSCONTENT, REGDT FROM BBS ORDER BY ID DESC";
            List<Bbs> bbs = jdbcTemplate.query(sql, this::boardMapper);
            return bbs;
        }catch (Exception e){
            e.printStackTrace();

            return null;
        }
    }

    /** 게시글 추가 */
    public boolean insert(Bbs bbs){

        String sql = "INSERT INTO BBS (ID, BBSTITLE, BBSCONTENT) VALUES (SEQ_BBS.nextVal, ?, ?)";
        int cnt = jdbcTemplate.update(sql, bbs.getBbsTitle(), bbs.getBbsContent());
        return cnt > 0;
    }

    /** 게시글 조회 */
    public Bbs bbsInfo(Long id){

        try{
            String sql = "SELECT * FROM BBS WHERE ID = ?";
            Bbs bbs = jdbcTemplate.queryForObject(sql, this::boardMapper, id);
            return bbs;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /** 게시글 수정 */
    public boolean update(Bbs bbs){

        String sql = "UPDATE BBS SET BBSTITLE = ?, BBSCONTENT = ?, UPDDT = SYSDATE WHERE ID = ?";
        int cnt = jdbcTemplate.update(sql, bbs.getBbsTitle(), bbs.getBbsContent(), bbs.getId());
        return cnt > 0;
    }

    /** 게시글 삭제 */
    public boolean delete(Bbs bbs){

        String sql = "DELETE FROM BBS WHERE ID = ?";
        int cnt = jdbcTemplate.update(sql, bbs.getId());
        return cnt > 0;
    }

    private Bbs boardMapper(ResultSet rs, int i) throws SQLException{
        Bbs bbs = new Bbs();
        bbs.setId(rs.getLong("ID"));
        bbs.setBbsTitle(rs.getString("BBSTITLE"));
        bbs.setBbsContent(rs.getString("BBSCONTENT"));
        bbs.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());

        return bbs;
    }

    /** 스케줄러 */
    public List<Bbs> schedulerCount() {
        System.out.println("%%%%%%%%%%%%%%%%%%%%");
        String sql = "SELECT TO_CHAR(REGDT, 'HH24') AS HOUR, COUNT(*) AS COUNT FROM BBS GROUP BY TO_CHAR(REGDT, 'HH24')";

        return null;
    }
}
