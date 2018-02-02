
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Explorer;
import domain.Status;
import domain.Trip;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select app from Application app where app.applicant.id = ?1 order by app.status")
	Collection<Application> findApplicationByExplorer(int id);

	@Query("select app from Application app where app.trip.manager.id=?1 order by app.status")
	Collection<Application> findApplicationByManager(int id);

	@Query("select app from Application app where app.applicant = ?1 order by app.status")
	Collection<Application> findByExplorer(Explorer explorer);

	@Query("select app.applicant from Application app where app.trip = ?1 order by app.status")
	Collection<Explorer> findApplicantsToTrip(Trip t);

	@Query("select ( 100 * (select count(a) from Application a where a.status = 'PENDING') / count(b))from Application b")
	Double pendingApplications();//C5

	@Query("select ( 100 * (select count(a) from Application a where a.status = 'DUE') / count(b))from Application b")
	Double dueApplications();//C6

	@Query("select ( 100 * (select count(a) from Application a where a.status = 'ACCEPTED') / count(b))from Application b")
	Double acceptedApplications();//C7

	@Query("select ( 100 * (select count(a) from Application a where a.status = 'CANCELLED') / count(b))from Application b")
	Double cancelledApplications();//C8

	@Query("select app from Application app where app.status = ?1 order by app.status")
	Collection<Application> findApplicationsByStatus(Status status);
}
