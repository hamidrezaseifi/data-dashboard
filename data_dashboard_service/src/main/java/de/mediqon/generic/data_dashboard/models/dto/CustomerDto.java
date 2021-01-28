package de.mediqon.generic.data_dashboard.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.mediqon.generic.data_dashboard.enums.ECustomerStatus;
import de.mediqon.generic.data_dashboard.models.base.BaseDto;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class CustomerDto extends BaseDto {

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String customerKey;

  @NotNull
  private ECustomerStatus status;


  public CustomerDto() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCustomerKey() {
    return customerKey;
  }

  public void setCustomerKey(String customerKey) {
    this.customerKey = customerKey;
  }

  public ECustomerStatus getStatus() {

    return this.status;
  }

  public void setStatus(final ECustomerStatus status) {

    this.status = status;
  }

  public void setStatusInt(final Integer status) {

    this.status = ECustomerStatus.fromValue(status);
  }

  public static CustomerDto generateNew(){
    CustomerDto customerDto = new CustomerDto();
    customerDto.status = ECustomerStatus.ACTIVE;

    return customerDto;
  }
}
