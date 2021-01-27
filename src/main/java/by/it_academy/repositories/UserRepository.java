package by.it_academy.repositories;

import by.it_academy.model.users.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"authenticate", "basket"})
    User findUserByAuthenticateLogin(String username);

    boolean existsUserByAuthenticateLogin(String login);

    @EntityGraph(attributePaths = {"authenticate", "basket"})
    @Query("select u from User u where u.id = ?1")
    User findUserWithBasketById(long id);

    @EntityGraph(attributePaths = "authenticate")
    @Query("select u from User u")
    List<User> findAllUsersWithAuthenticate();

    @EntityGraph(attributePaths = "authenticate")
    @Query("select u from User u where u.id = ?1")
    User findUserWithAuthenticateById(long userId);

    @Query("select u.basket.basketCells.size from User u where u.id = ?1")
    int getSizeUserBasketCellsById(long id);

    @EntityGraph(attributePaths = {"basket.basketCells", "basket.basketCells.book"})
    @Query("from User u where u.id = ?1")
    User findUserWithBasketCellsWithBooksById(long id);

    @EntityGraph(attributePaths = "authenticate")
    @Query("from User u where u.role = 'ADMINISTRATOR'")
    List<User> findAllAdministrators();
}