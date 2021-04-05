package com.freaky.fit.repositories;



import com.freaky.fit.documents.UserDetails;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CouchbaseRepository<UserDetails,String> {
}
