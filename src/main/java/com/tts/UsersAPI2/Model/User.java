package com.tts.UsersAPI2.Model;

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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Length(max = 20, message = "Your first name cannot have more than 20 characters")
  private String firstName;

  @Length(min = 2, message = "Your last name must have at least 2 characters")
  private String lastName;

  @Length(min = 4, message = "Your state must have at least 4 characters")
  @Length(max = 20, message = "Your state cannot have more than 20 characters")
  private String state;

}
