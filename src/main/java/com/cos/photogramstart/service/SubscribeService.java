package com.cos.photogramstart.service;

import com.cos.photogramstart.repository.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.SubscribeDto;
import lombok.RequiredArgsConstructor;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional
    public void 구독하기(Long fromUserId, Long toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 했습니다.");
        }
    }

    @Transactional
    public void 구독취소하기(Long fromUserId, Long toUserId) {

        subscribeRepository.mUnSubscribe(fromUserId, toUserId);

    }

    @Transactional(readOnly = true)
    public List<SubscribeDto> 구독리스트(Long principalId, Long pageUserId) {


        //쿼리 준비

        StringBuffer sb = new StringBuffer();
        sb.append("select u.id, u.username, u.profileImageUrl,");
        sb.append("if((select 1 from subscribe where from_user_id = ? and to_user_id = u.id), 1, 0) subscribeState,");
        sb.append("if((?=u.id), 1, 0) equalUserState ");
        sb.append("from user u inner join subscribe s ");
        sb.append("on u.id = s.to_user_id ");
        sb.append("where s.from_user_id = ? "); // 세미콜론 첨부하면 안됨


        //1. 물음표 : principalId
        //2. 물음표 : principalId
        //3. 물음표 : pageUserId

        //쿼리완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);


        //쿼리 실행 (qlrm 라이브러리 필요 = Dto에 Db결과를 매핑하기 위해서)
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }
}
