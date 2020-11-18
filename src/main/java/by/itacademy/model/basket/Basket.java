package by.itacademy.model.basket;

import by.itacademy.model.users.User;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})

@Entity
@Table
public class Basket {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "basket", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BasketCell> basketcells = new HashSet<>();

    @OneToOne
    private User user;

    public void addBasketCell(BasketCell basketCell) {
        this.basketcells.add(basketCell);
        basketCell.setBasket(this);
    }

    public void removeBasketCell(BasketCell basketCell) {
        this.basketcells.remove(basketCell);
        basketCell.setBasket(null);
    }
}
