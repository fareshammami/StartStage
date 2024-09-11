package events.dewdrop.Rest;

import com.eventstore.dbclient.ReadResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.dewdrop.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/events")
public class  StreamEventController {

    private final EventService eventService;

    @Autowired
    public StreamEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/query/{eventType}")
    public Object queryFirstEvent(@PathVariable String eventType) {
        try {
            ReadResult readResult = eventService.queryEvents(eventType);

            byte[] eventDataBytes = readResult.getEvents().get(0).getEvent().getEventData();
            String decodedJson = new String(eventDataBytes, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Object decodedObject = objectMapper.readValue(decodedJson, Object.class);
            System.out.println("Decoded object: " + decodedObject);

            return decodedObject;

        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
            return "Error retrieving or decoding event data";
        }
    }
}
