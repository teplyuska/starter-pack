package starter.pack.core.repository.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * While this class doesn't seem to be used, it does!
 * It will be used like so:
 *
 * @Type(type = "starter.pack.core.repository.util.NameEnumType")
 * It will enable Hibernate to write an Enum property to the database as its .name() value, to a column of type varchar.
 * It will also enable Hibernate to map the column back to the corresponding enum when reading.
 */
@SuppressWarnings("rawtypes")
public final class NameEnumType implements DynamicParameterizedType, UserType {
    private Class<? extends Enum> enumClass;

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String name = resultSet.getString(strings[0]);
        if (resultSet.wasNull()) {
            return null;
        }

        for (Enum value : returnedClass().getEnumConstants()) {
            if (value instanceof Enum) {
                Enum nameEnum = value;
                if (nameEnum.name().equals(name)) {
                    return value;
                }
            }
        }

        throw new IllegalStateException("Unknown " + returnedClass().getSimpleName() + " label");
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setNull(i, Types.VARCHAR);
        } else {
            preparedStatement.setString(i, ((Enum) o).name());
        }
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @Override
    public Class<? extends Enum> returnedClass() {
        return enumClass;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setParameterValues(Properties parameters) {
        ParameterType params = (ParameterType) parameters.get(PARAMETER_TYPE);
        enumClass = params.getReturnedClass();
    }
}