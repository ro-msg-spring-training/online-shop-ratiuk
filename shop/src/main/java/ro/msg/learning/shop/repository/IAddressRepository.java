package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import ro.msg.learning.shop.model.Address;

public interface IAddressRepository extends IBaseRepository<Address, Long> {
    @Query(value = "Select a from Address a where a.addressCountry=:addressCountry and a.addressCity=:addressCity and a.addressCounty=:addressCounty and a.addressStreet=:addressStreet")
    Address findByAddressCountryAndAddressCityAndAddressCountyAndAddressStreet(String addressCountry, String addressCity, String addressCounty, String addressStreet);
}
