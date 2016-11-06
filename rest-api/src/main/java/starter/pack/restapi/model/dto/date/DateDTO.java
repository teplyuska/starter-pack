package starter.pack.restapi.model.dto.date;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class DateDTO {
    private LocalDateTime utcLocal;
    private LocalDateTime copenhagenLocal;
    private ZonedDateTime utcZoned;
    private ZonedDateTime copenhagenZoned;

    public DateDTO() {
    }

    public LocalDateTime getUtcLocal() {
        return utcLocal;
    }

    public void setUtcLocal(LocalDateTime utcLocal) {
        this.utcLocal = utcLocal;
    }

    public LocalDateTime getCopenhagenLocal() {
        return copenhagenLocal;
    }

    public void setCopenhagenLocal(LocalDateTime copenhagenLocal) {
        this.copenhagenLocal = copenhagenLocal;
    }

    public ZonedDateTime getUtcZoned() {
        return utcZoned;
    }

    public void setUtcZoned(ZonedDateTime utcZoned) {
        this.utcZoned = utcZoned;
    }

    public ZonedDateTime getCopenhagenZoned() {
        return copenhagenZoned;
    }

    public void setCopenhagenZoned(ZonedDateTime copenhagenZoned) {
        this.copenhagenZoned = copenhagenZoned;
    }
}
