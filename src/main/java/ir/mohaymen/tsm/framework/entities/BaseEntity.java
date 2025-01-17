package ir.mohaymen.tsm.framework.entities;

import ir.mohaymen.tsm.framework.events.Event;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true, updatable = false)
    @Getter
    protected long id;
    @Transient
    protected List<Event> events = new ArrayList<>();

    protected void handleEvent(Event event){
        setStateByEvent(event);
        invariantValidation();
        raiseEvent(event);
    }

    private void raiseEvent(Event event) {
        events.add(event);
    }

    protected abstract void setStateByEvent(Event event);

    protected abstract void invariantValidation();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
