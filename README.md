# ProductHub

ProductHub는 상품과 관련된 다양한 기능을 지원하는 멀티 모듈 프로젝트입니다. 스프링 프레임워크를 기반으로 하며, 구조적인 계층을 통해 각 레이어가 역할에 맞는 책임을 담당하도록 설계되었습니다.

---

## **Modules**

### Core
Core 모듈은 각 도메인 서비스를 개별 하위 모듈로 관리하며, 서비스의 성장에 따라 구조가 유연하게 확장될 수 있도록 합니다. 각 모듈은 도메인에 대한 책임을 지고, 서비스 기능을 중심으로 설계되었습니다.

- **core-api**: 프로젝트 내 공통 API 구성 요소와 인터페이스를 제공합니다. 프레젠테이션 레이어로 기능을 분리하여, 하위 레이어와의 순환 참조를 방지합니다.
- **core-enum**: 공통적으로 사용되는 Enum 정의를 관리하여 통일된 데이터 관리를 지원합니다.

### Support
Support 모듈은 로깅, 유틸리티 클래스 등 공통 기능을 제공합니다. 프로젝트 전반에서 재사용할 수 있는 유틸리티와 로깅 기능을 제공하여, 각 도메인 모듈이 독립적으로 기능을 구현할 수 있도록 지원합니다.

- **logging**: 애플리케이션 전반의 로깅을 담당하며, 추후 확장성 있는 로깅 설정을 지원합니다.
- **utils**: 자주 사용되는 유틸리티 메서드들을 관리하여, 코드 재사용성을 높입니다.

### Storage
Storage 모듈은 데이터 액세스 계층으로, 데이터베이스와의 상호작용을 담당합니다. JPA와 QueryDSL을 사용하여 복잡한 쿼리 요구 사항을 효율적으로 처리하며, 추후 성능 향상이 필요한 경우 JdbcTemplate을 통해 배치 작업도 지원할 계획입니다.

- **db-core**: 데이터베이스와 상호작용하는 모든 작업을 처리하며, 모든 엔티티와 데이터 액세스 로직을 포함합니다.

### Domain
Domain 모듈은 비즈니스 로직을 담당하며, 각 도메인에 맞는 서비스 로직을 처리합니다. 각 도메인은 독립적인 비즈니스 로직을 관리하며, 각 엔티티와 관련된 유효성 검증, 비즈니스 정책 등을 포함합니다.

- **product-core**: 상품 관련 비즈니스 로직을 관리합니다. 상품의 등록, 수정, 삭제와 같은 기본적인 비즈니스 기능뿐만 아니라, 추가적인 상품 정책을 구현합니다.

### Tests
테스트 모듈은 API 문서화 및 테스트를 포함합니다. RestDocs를 사용하여 API 문서화를 자동화하고, Asciidoctor를 통해 문서를 구성합니다.

- **api-docs**: API 문서화 및 테스트 환경을 제공하여, 각 기능별 API 명세를 정확히 작성할 수 있도록 합니다.

---


## **Dependency Management**

모든 의존성 버전 관리는 `gradle.properties` 파일을 통해 관리하며, 동일한 버전의 라이브러리를 사용하여 코드의 일관성을 유지하고, 관리의 복잡성을 최소화했습니다.

- **Asciidoctor & RestDocs**: API 문서화를 위해 Asciidoctor와 RestDocs를 사용해 모든 기능별 API 명세를 자동으로 작성합니다.

---

## **Future Plans**

이 프로젝트는 등록된 상품 데이터를 LLM에 학습시켜 **외부 상품 등록을 자동화**할 수 있는 초기 상품 관리 서버로 활용됩니다. 이를 통해 상품 정보의 자동화된 관리와 연동을 강화하여 서비스 확장성을 높이는 것을 목표로 하고 있습니다.
