package dev.razafindratelo.tapakilaBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventTurnover {
    private String eventId;
    private List<Turnover> eventTurnover;

    @Override
    public String toString() {
        return "EventTurnover {" +
                   "\n \t eventId=" + eventId +
                   "\n \t eventTurnover=" + eventTurnover +
                "\n}";
    }
}
