package events.dewdrop.config;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.springframework.stereotype.Service;

@Service
public class  EventStoreDB {

public EventStoreDBClient eventStoreDBClient() {
    EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow("esdb://localhost:2113?tls=false");
    return EventStoreDBClient.create(settings);
}
}
