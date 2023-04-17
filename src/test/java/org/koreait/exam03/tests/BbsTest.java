package org.koreait.exam03.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.exam03.controllers.boards.BbsParam;
import org.koreait.exam03.models.board.Bbs;
import org.koreait.exam03.models.board.BbsDAO;
import org.koreait.exam03.models.board.BbsInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional
public class BbsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BbsInsertService service;

    @Autowired
    private BbsDAO dao;

    private Bbs bbs;
    private BbsParam bbsParam;

    @BeforeEach
    public void init() {
        bbs = new Bbs();
        bbs.setBbsTitle("테스트 제목");
        bbs.setBbsContent("테스트 내용");

        bbsParam = new BbsParam();
        bbsParam.setBbsTitle("테스트 제목 저장");
        bbsParam.setBbsContent("테스트 내용 저장");
    }

    @Test
    @DisplayName("게시글 등록성공")
    public void saveSuccess() {
        assertDoesNotThrow(() -> {
            service.dataInsert(bbsParam);
        });
    }

    @Test
    @DisplayName("게시글 데이터 디비등록 성공 시 true 반환")
    public void dataInsertTest() {
        boolean result = dao.insert(bbs);

        assertTrue(result);
    }

    // 통합 테스트
    @Test
    @DisplayName("게시글 제목 또는 내용이 누락되었을 경우 제목을 입력하세요, 내용을 입력하세요 출력 여부")
    public void saveValidationResponseTest() throws Exception {
        String body = mockMvc.perform(post("/bbs/bbsInsert"))
                .andReturn().getResponse().getContentAsString();

        boolean result1 = body.contains("제목을 입력해 주세요.");
        assertTrue(result1);

        boolean result2 = body.contains("내용을 입력해 주세요.");
        assertTrue(result2);
    }

    @Test
    @DisplayName("게시글 작성 성공시 /bbs로 페이지 이동")
    public void saveSuccessRedirectTest() throws Exception {
        mockMvc.perform(post("/bbs/bbsInsert")
                        .param("bbsTitle", "테스트 제목")
                        .param("bbsContent", "테스트 내용"))
                .andDo(print())
                .andExpect(redirectedUrl("/bbs"));
    }
}
