package pl.coderslab.travelplannerandjournal.model.dto;

import lombok.*;
import pl.coderslab.travelplannerandjournal.model.entity.JournalEntry;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntryResponse {

    private Long id;
    private String content;
    private String tripName;

    public static JournalEntryResponse toResponse(JournalEntry journalEntry) {
        return JournalEntryResponse.builder()
                .id(journalEntry.getId())
                .content(journalEntry.getContent())
                .tripName(journalEntry.getTrip().getName())
                .build();
    }
}
