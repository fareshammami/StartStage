package events.dewdrop.service;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadAllOptions;
import com.eventstore.dbclient.ReadResult;
import events.dewdrop.config.EventStoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class EventService {

    private final EventStoreDB eventStoreDB;

    @Autowired
    public EventService(EventStoreDB eventStoreDB) {
        this.eventStoreDB = eventStoreDB;
    }

    public ReadResult queryEvents(String eventType) throws ExecutionException, InterruptedException {
        EventStoreDBClient client = eventStoreDB.eventStoreDBClient();
        ReadAllOptions options = ReadAllOptions.get()
                .forwards()
                .fromStart();

        return client.readAll(options).get();
    }

}