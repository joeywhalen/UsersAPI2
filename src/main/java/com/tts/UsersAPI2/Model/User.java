package com.tts.UsersAPI2.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
  //Checking oon a push to GitHub issue
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(notes = "This is the user id")
  private Long id;

  @Length(max = 20, message = "Your first name cannot have more than 20 characters")
  @ApiModelProperty(notes = "This is the first name of the user")
  private String firstName;

  @Length(min = 2, message = "Your last name must have at least 2 characters")
  @ApiModelProperty(notes = "This is the last name of the user")
  private String lastName;

  @Length(min = 4, message = "Your state must have at least 4 characters")
  @Length(max = 20, message = "Your state cannot have more than 20 characters")
  @ApiModelProperty(notes = "This is the state of residence for the user")
  private String state;

}
