# **Storage Module Index Convention**

## **목적**
### 최초 설계시 존재했으나 스펙 다운 결정 추후 MYSQL 성능 지표를 통해 도입할지 고민
Storage 모듈은 데이터 저장 및 조회를 담당하며, 데이터 접근 계층의 일관성과 가독성을 높이기 위해 명확한 네이밍 컨벤션을 사용합니다.  
이 문서는 클래스 및 메서드 네이밍 규칙을 정의하여 개발자 간 협업을 원활하게 하고 유지보수성을 향상시키는 것을 목표로 합니다.

---

## **클래스 네이밍 컨벤션**

### **1. 레포지토리 인터페이스**
- **역할**: 데이터 저장, 수정, 삭제 작업의 추상화를 정의.
- **네이밍 규칙**: `XXPersistenceRepository`
    - **예**:
        - `ProductGroupPersistenceRepository`
        - `UserPersistenceRepository`

### **2. 구현체**
#### **JPA 기반 구현체**
- **역할**: JPA를 이용한 데이터 접근 로직 정의.
- **네이밍 규칙**: `XXJpaRepository`
    - **예**:
        - `ProductGroupJpaRepository`
        - `UserJpaRepository`

#### **JDBC 기반 구현체**
- **역할**: JDBC를 이용한 데이터 접근 로직 정의.
- **네이밍 규칙**: `XXJdbcRepository`
    - **예**:
        - `ProductGroupJdbcRepository`
        - `UserJdbcRepository`

#### **JPA와 JDBC를 함께 사용하는 구현체**
- **역할**: JPA와 JDBC를 조합한 데이터 접근 로직 정의.
- **네이밍 규칙**: `XXHybridRepository`
    - **예**:
        - `ProductGroupHybridRepository`
        - `UserHybridRepository`

---

## **메서드 네이밍 컨벤션**

### **1. 기본 형식**
- **구조**: `동사 + 작업 대상`
    - **동사**: 메서드가 수행하는 작업 (예: `save`, `update`, `delete`, `fetch` 등).
    - **작업 대상**: 작업의 대상이 되는 엔티티나 데이터 (예: `ProductGroup`, `Configs` 등).

---

### **2. CRUD 작업별 규칙**

#### **저장/수정**
- **단일 항목 저장**: `save<Entity>`
    - **예**: `saveProductGroupConfig`
- **여러 항목 저장**: `saveAll<Entity>`
    - **예**: `saveAllProductGroupConfigs`
- **수정 작업**: `update<Entity>`
    - **예**: `updateProductGroupNameConfigs`

#### **삭제**
- **단일 항목 삭제**: `delete<Entity>`
    - **예**: `deleteProductGroup`
- **조건에 따른 삭제**: `deleteBy<Condition>`
    - **예**: `deleteByStatus`

#### **조회**
- **단일 항목 조회**: `find<Entity>` 또는 `fetch<Entity>`
    - **예**: `findProductGroupById`
- **조건부 조회**: `findBy<Condition>` 또는 `fetchBy<Condition>`
    - **예**: `fetchProductGroupConfigsByStatus`
- **모두 조회**: `findAll<Entity>` 또는 `fetchAll<Entity>`
    - **예**: `fetchAllProductGroupConfigs`

---

## **예시**

### **1. 인터페이스**
```java
public interface ProductGroupPersistenceRepository {
    void saveProductGroupConfig(ProductGroupConfigEntity configEntity);
    void saveAllProductGroupConfigs(List<ProductGroupConfigEntity> configEntities);
    void updateProductGroupNameConfigs(List<ProductGroupNameConfigDto> configDtos);
    void deleteProductGroupConfig(Long configId);
}
```
---

## **결론**

### **클래스 네이밍**
1. **인터페이스**: `XXPersistenceRepository`
    - 데이터 저장 로직의 추상화.
2. **구현체**:
    - JPA 기반: `XXJpaRepository`
    - JDBC 기반: `XXJdbcRepository`
    - JPA+JDBC 혼합: `XXHybridRepository`

### **메서드 네이밍**
- **저장**: `save<Entity>` / `saveAll<Entity>`
- **수정**: `update<Entity>`/ `supdateAll<Entity>`
- **조회**: `fetch<Entity>` / `fetchBy<Condition>`

이 컨벤션을 따르면 코드의 가독성과 유지보수성이 향상되며, 역할과 책임이 명확히 구분됩니다.
