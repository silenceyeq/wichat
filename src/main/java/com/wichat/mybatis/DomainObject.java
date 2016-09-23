package com.wichat.mybatis;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.beans.BeanUtils;

public abstract class DomainObject {

	public abstract Object getId();
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof DomainObject)) {
			return false;
		}
		if(obj == this) {
			return true;
		}
		
		DomainObject domainObject = (DomainObject)obj;
		if(domainObject.getId() == null) {
			return false;
		}
		return new EqualsBuilder()
			.append(this.getId(), domainObject.getId())
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder(17, 37)
			.append(this.getClass())
			.append(this.getId())
			.toHashCode();
	}
	
	/**将领域对象转为视图模型
	 * @param domainObject 领域对象
	 * @param modelType 视图模型类型
	 * @return 视图模型
	 */
	public <TDomainObject extends DomainObject, TModel> TModel convertDomainObjectToModel(
			TDomainObject domainObject, Class<TModel> modelType) {
		
		TModel model = null;
		try {
			model = modelType.newInstance();
		} catch (Throwable ex) {
			return null;
		}
		
		BeanUtils.copyProperties(domainObject, model);
		
		return model;
	}

}
