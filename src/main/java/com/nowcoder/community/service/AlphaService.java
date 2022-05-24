package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.Transient;
import java.util.Date;

@Service
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public AlphaService() {
//        System.out.println("实例化AlphaService");
    }

    @PostConstruct
    public void init() {
//        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    public void destroy() {
//        System.out.println("销毁AlphaService");
    }

    public String find() {
        return alphaDao.select();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Object save1(){
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.md5("123" + user.getStatus()));
        user.setHeaderUrl("http://images.nowcoder.com/head/149t.png");
        user.setEmail("alpha@163.com");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        discussPost.setTitle("hello");
        discussPost.setContent("new people");
        discussPost.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(discussPost);

        Integer.valueOf("abc");

        return "ok";
    }

    public Object save2(){
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0,5));
                user.setPassword(CommunityUtil.md5("123" + user.getStatus()));
                user.setHeaderUrl("http://images.nowcoder.com/head/110t.png");
                user.setEmail("beta@163.com");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                DiscussPost discussPost = new DiscussPost();
                discussPost.setUserId(user.getId());
                discussPost.setTitle("new");
                discussPost.setContent("new people");
                discussPost.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(discussPost);

                Integer.valueOf("abc");

                return "ok";
            }
        });
    }
}
