# DB CORE 상품 관련 패키지

상품 관리 모듈 내 `DB CORE`의 각 테이블과 관계를 다음과 같이 설명합니다. 인덱스는 성능을 고려해 설정했으며, 외래 키는 운영 유지보수를 위해 설정하지 않았습니다. 이는 운영 중 장애가 발생할 경우 유연한 대응을 위해 결정한 사항입니다.

본 패키지에서는 **기본적으로 JPA와 QueryDSL을 사용하여 데이터를 처리**합니다. **저장 시에는 JPA Repository 및  JdbcTemplate 을 사용하고, 조회는 QueryDSL을 활용**하여 복잡한 조건을 효율적으로 처리합니다.

RDS - 운영 MYSQL, 테스트 H2

## 테이블 개요 및 관계도

### 1. `PRODUCT_GROUP`
- **설명**: 상품 그룹에 대한 정보를 저장하며, 개별 상품들이 속하는 최상위 그룹입니다.
- **주요 필드**: `productGroupName`(상품 그룹 이름), `styleCode`(스타일 코드), `categoryId`, `brandId`, `sellerId`, `productCondition`(상품 상태), `managementType`(관리 타입), `optionType`(옵션 타입), `regularPrice`(정가), `currentPrice`(판매가).
- **관계**: `PRODUCT`, `PRODUCT_NOTICE`, `PRODUCT_DELIVERY`, `PRODUCT_GROUP_IMAGE`, `PRODUCT_GROUP_DETAIL_DESCRIPTION`와 연관됩니다.
- **인덱스**: `sellerId`, `categoryId`, `brandId`, `styleCode`

### 2. `PRODUCT`
- **설명**: 개별 상품 정보를 저장하며, `PRODUCT_GROUP`의 하위 단위입니다.
- **주요 필드**: `productGroupId`, `soldOutYn`(품절 여부), `displayYn`(표시 여부), `quantity`(수량), `additionalPrice`(추가 가격).
- **관계**: `PRODUCT_OPTION`와 연관됩니다.
- **인덱스**: `productGroupId`

### 3. `PRODUCT_OPTION`
- **설명**: 개별 상품에 대한 옵션 정보를 매핑하는 테이블로, 옵션 그룹과 옵션 상세를 연결합니다.
- **주요 필드**: `productId`, `optionGroupId`, `optionDetailId`.
- **관계**: `PRODUCT`, `OPTION_GROUP`, `OPTION_DETAIL`와 연관됩니다.
- **인덱스**: `productId`, `optionGroupId`, `optionDetailId`

### 4. `OPTION_GROUP`
- **설명**: 옵션 그룹 정보를 저장하며, 각 옵션 이름(`OptionName`)을 관리합니다.
- **주요 필드**: `optionName`(예: `SIZE`, `COLOR`).
- **관계**: `OPTION_DETAIL`와 연관됩니다.

### 5. `OPTION_DETAIL`
- **설명**: 각 옵션 그룹의 상세 정보로, 옵션 값(예: `블랙`, `S`)을 저장합니다.
- **주요 필드**: `optionGroupId`, `optionValue`.
- **관계**: `OPTION_GROUP`와 연결되며 `PRODUCT_OPTION`에 의해 `PRODUCT`와 매핑됩니다.
- **인덱스**: `optionGroupId`

### 6. `PRODUCT_NOTICE`
- **설명**: 상품의 상세 설명 정보를 저장하는 테이블로, 주로 상품의 재질, 제조사, 사이즈 등의 정보를 포함합니다.
- **주요 필드**: `productGroupId`, `material`, `color`, `size`, `maker`, `origin`, `washingMethod`, `yearMonth`, `assuranceStandard`, `asPhone`.
- **관계**: `PRODUCT_GROUP`와 연관됩니다.
- **인덱스**: `productGroupId`

### 7. `PRODUCT_DELIVERY`
- **설명**: 상품의 배송 및 반품 정보를 저장하는 테이블로, 상품의 배송 지역, 배송료, 반품 방식 등의 정보를 포함합니다.
- **주요 필드**: `productGroupId`, `deliveryArea`, `deliveryFee`, `deliveryPeriodAverage`, `returnMethodDomestic`, `returnCourierDomestic`, `returnChargeDomestic`, `returnExchangeAreaDomestic`.
- **관계**: `PRODUCT_GROUP`와 연관됩니다.
- **인덱스**: `productGroupId`

### 8. `PRODUCT_GROUP_IMAGE`
- **설명**: 상품 그룹에 대한 이미지를 저장하며, 다양한 이미지 유형을 지원합니다(예: 썸네일, 상세 이미지).
- **주요 필드**: `productGroupId`, `productImageType`, `imageUrl`.
- **관계**: `PRODUCT_GROUP`와 연관됩니다.
- **인덱스**: `productGroupId`

### 9. `PRODUCT_GROUP_DETAIL_DESCRIPTION`
- **설명**: 상품 그룹의 상세 설명을 LOB 형태로 저장하는 테이블입니다.
- **주요 필드**: `productGroupId`, `detailDescription`.
- **관계**: `PRODUCT_GROUP`와 연관됩니다.
- **인덱스**: `productGroupId`

### 10. `BRAND`
- **설명**: 브랜드 정보를 저장하는 테이블로, 각 브랜드의 이름과 아이콘 이미지 URL, 표시 여부를 포함합니다.
- **주요 필드**: `brandName`(브랜드 이름), `brandIconImageUrl`(아이콘 이미지 URL), `displayYn`(표시 여부).

### 11. `CATEGORY`
- **설명**: 상품의 카테고리 정보를 저장하는 테이블로, 계층형 구조를 지원합니다.
- **주요 필드**: `categoryName`(카테고리 이름), `depth`(카테고리 계층 깊이), `parentCategoryId`(상위 카테고리 ID), `displayYn`(표시 여부), `targetGroup`(타겟 그룹), `categoryType`(카테고리 타입), `path`(카테고리 경로).

### 11. `COLOR`
- **설명**: 상품 옵션으로 사용될 색상 정보를 저장하는 테이블로, 각 색상 이름만을 저장합니다.
- **주요 필드**: `colorName` (색상 이름).


