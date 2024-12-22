# **Storage Module **

---
### 패키지 구조
```
├── storage/                         # 데이터 저장소 관련 모듈
│   ├── build/                       # 데이터 저장소 모듈 빌드 폴더
│   └── db-core/                     # 데이터베이스 접근 및 엔티티 관리 모듈
│   └── db-cache/                    # REDIS 접근 및 캐시 관리 모듈
│   └── db-index/                    # NOSQL(ElasticSearch) 접근 및 도큐먼트 관리 모듈
```
---


### **패키지 설명**
1. **`db-core`**
    - RDBMS(MySQL 등)에 접근하는 엔티티 및 저장소 관련 코드를 포함.
    - QueryDSL 기반의 동적 쿼리 지원.
    - JPA와 JDBC를 병행하여 데이터 저장/조회 최적화.

2. **`db-cache`**
    - Redis를 기반으로 캐시 기능을 제공.
    - **Redisson**을 활용하여 분산 락과 캐싱을 지원.
    - 고속 데이터 접근과 단순 데이터 조회에 적합.

3. **`db-index`**
    - **최초 설계에는 있었으나 스펙 다운 결정** 
    - ElasticSearch를 통해 NoSQL 문서 기반 데이터를 관리.
    - 대량 데이터 검색 및 색인 최적화.



## **설계 철학**
### **1. 외부 의존성 추상화**
- **외부 도메인 모듈에서 Storage 모듈의 세부 구현체를 몰라야 합니다.**
    - Storage 모듈의 구현체에 접근하지 않고, 인터페이스를 통해 통신합니다.
    - 이를 통해 도메인 계층과 데이터 계층 간의 결합도를 낮추고 유지보수성을 높입니다.

#### **예시**
```java
// 인터페이스 정의 (db-core)
public interface ProductPersistenceRepository {
    ProductEntity findById(Long id);
    void save(ProductEntity entity);
}

// 구현체 (db-core)
@Repository
public class ProductJpaRepository implements ProductPersistenceRepository {
    @Override
    public ProductEntity findById(Long id) {
        // JPA 로직
    }

    @Override
    public void save(ProductEntity entity) {
        // JPA 저장 로직 or JDBC 저장 로직
    }
}

// 도메인 계층에서 인터페이스 사용
@Service
public class ProductFinder {
    
    private final ProductPersistenceRepository productRepository;

    public ProductService(ProductPersistenceRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id);
    }
}
```



## **2. 데이터 접근 규칙**
모든 데이터 접근은 **명확한 네이밍 컨벤션**을 따릅니다.  
조회 메서드는 `fetch` 를, 저장 메서드는 `save`를,  업데이트 메서드는 `update`를 사용합니다.

### **컨벤션 요약**
| 작업 유형         | 메서드 네이밍 예시                            |
|---------------|----------------------------------------------|
| **단일 데이터 조회** | `find<Entity>` 또는 `fetch<Entity>`           |
| **조건부 조회**    | `findBy<Condition>` 또는 `fetchBy<Condition>` |
| **데이터 저장**    | `save<Entity>`                               |
| **데이터 업데이트**  | `update<Entity>` 또는 `updateBy<Condition>`  |

---

## **3. 기술 스택**
- **RDBMS**: MySQL
- **캐시**: Redis (Redisson 기반)
- **NoSQL**: ElasticSearch
- **ORM/쿼리**: JPA + QueryDSL

---

## **베스트 프랙티스**

### **1. 인터페이스 기반 설계**
- 모든 데이터 접근은 **인터페이스를 통해 이루어집니다**.
- 구현체에 의존하지 않음으로써 테스트 용이성과 확장성을 확보합니다.

---

### **2. 단일 책임 원칙**
- 각 패키지는 특정 데이터 저장소에 대한 책임만 가집니다.
- 예:
    - **`db-core`**: RDBMS
    - **`db-cache`**: Redis
    - **`db-index`**: ElasticSearch

---

### **3. 명확한 네이밍 컨벤션**
- 클래스와 메서드 이름에서 **역할과 책임이 명확히 드러나야 합니다**.

---

## **향후 계획**

### **1. 모니터링 및 성능 최적화**
- Redis와 ElasticSearch의 성능 지표를 수집하고 최적화 로직을 추가합니다.

### **2. 다중 저장소 간 데이터 동기화**
- RDBMS와 ElasticSearch 간의 데이터 동기화 방안을 마련합니다.

---

Storage 모듈은 프로젝트의 데이터 접근 계층을 추상화하여 유지보수성을 높이고,  
다양한 데이터 저장소를 효과적으로 활용할 수 있도록 설계되었습니다.
