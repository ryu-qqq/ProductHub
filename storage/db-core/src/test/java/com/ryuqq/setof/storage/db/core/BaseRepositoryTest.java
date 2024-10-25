package com.ryuqq.setof.storage.db.core;

import com.ryuqq.setof.storage.db.core.brand.BrandQueryDslRepository;
import com.ryuqq.setof.storage.db.core.category.CategoryQueryDslRepository;
import com.ryuqq.setof.storage.db.core.color.ColorQueryDslRepository;
import com.ryuqq.setof.storage.db.core.config.JpaConfiguration;
import com.ryuqq.setof.storage.db.core.config.StorageModuleTestConfig;
import com.ryuqq.setof.storage.db.core.config.TestQueryDslConfig;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

//@ActiveProfiles("local")
//@SpringBootTest
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@Tag("context")

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import({
        TestQueryDslConfig.class,
        StorageModuleTestConfig.class,
        BrandQueryDslRepository.class,
        CategoryQueryDslRepository.class,
        ColorQueryDslRepository.class,



})
public abstract class BaseRepositoryTest {

    @Autowired
    private EntityManager em;

    protected EntityManager getEntityManager(){
        return em;
    }


    protected void flushAndClear() {
        flush();
        clear();
    }

    protected void flush(){
        em.flush();
    }

    protected void clear(){
        em.clear();
    }

}
