package peyto.ide.core.service.types;

public enum SourceGroupType {

	MYBATIS(
		SourceCodeType.MYBATIS_DAO
		, SourceCodeType.MYBATIS_TEST_DAO
		, SourceCodeType.MYBATIS_MAPPER
		, SourceCodeType.MYBATIS_DTO
	),
	SPRING_JPA(
		SourceCodeType.SPRING_JPA_CODE
		, SourceCodeType.SPRING_JPA_TEST_CODE
		, SourceCodeType.SPRING_JPA_ENTITY
	),
	SPRING_REST(
		SourceCodeType.SPRING_REST_CODE
		, SourceCodeType.SPRING_REST_TEST_CODE
		, SourceCodeType.SPRING_REST_DATA
	),
	FLASK_REST(
		SourceCodeType.FLASK_REST_CODE
		, SourceCodeType.FLASK_REST_TEST_CODE
		, SourceCodeType.FLASK_REST_DATA		
	),
	REACT_TS_REST(
		SourceCodeType.REACT_TS_REST
		, SourceCodeType.REACT_TS_TEST_REST
		, SourceCodeType.REACT_TS_TYPE
	);
	
	private SourceCodeType[] types;
	
	private SourceGroupType(SourceCodeType... types) {
		this.types = types;
	}
	
	public SourceCodeType[] getTypes() {
		return this.types;
	}
	
}