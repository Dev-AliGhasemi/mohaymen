package ir.mohaymen.tsm.framework.value_objects;

public abstract class BaseValueObjects<T> {
    protected abstract int getHashCode();

    protected abstract boolean isEqual(T obj);

    @Override
    public int hashCode() {
        return getHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return isEqual((T) obj);
    }
}
