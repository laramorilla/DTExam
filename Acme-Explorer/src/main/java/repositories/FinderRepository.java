
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Trip;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select f from Finder f join f.trips t where t = ?1")
	Collection<Finder> findFinderByTrip(Trip trip);

	@Query("select tr from Trip tr where(tr.title LIKE %?1% or tr.description LIKE %?1% or tr.ticker LIKE %?1%) and (tr.startDateTrip >= ?2 and tr.endDateTrip <= ?3) and tr.price >= ?4 and tr.price <= ?5")
	Collection<Trip> findTripsPerFinder(String keyword, Date date1, Date date2, Double price1, Double price2);
}
