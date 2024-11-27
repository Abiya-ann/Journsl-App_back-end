package com.example.journal.Entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class UserRolesId implements Serializable {
    private Long userId;
    private Integer roleId;
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if( o == null || getClass() != o.getClass()) return false;
        UserRolesId that = (UserRolesId)o;
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId,roleId);
    }


}
