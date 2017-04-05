package com.friends.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sengkai on 3/17/2017.
 */
@Entity
public class ProfileAccount implements Serializable {

  private static final long serialVersionUID = -2247770723875391267L;
  private String emailAddress;

  public ProfileAccount() {
  }

  public ProfileAccount(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Id
  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileAccount profileAccount = (ProfileAccount) o;
    return Objects.equals(emailAddress, profileAccount.emailAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailAddress);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("name", emailAddress)
        .toString();
  }
}
