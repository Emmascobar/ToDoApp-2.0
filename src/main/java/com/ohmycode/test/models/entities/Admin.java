package com.ohmycode.test.models.entities;

import com.ohmycode.test.models.utils.Address;

import javax.persistence.Entity;
import java.util.Collection;


@Entity
public class Admin extends User {
    public Admin() {
    }

    public Admin(String name, String username, String password, Address address, Collection<Role> roles) {
        super(name, username, password, address, roles);
    }
}
