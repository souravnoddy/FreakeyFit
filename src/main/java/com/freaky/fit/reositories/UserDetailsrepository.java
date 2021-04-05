package com.freaky.fit.reositories;

import com.freaky.fit.entities.UserDetails;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsrepository extends PagingAndSortingRepository<UserDetails,String> {
}
