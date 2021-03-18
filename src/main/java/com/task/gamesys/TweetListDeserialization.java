package com.task.gamesys;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class TweetListDeserialization extends StdDeserializer<Tweet> {

    public TweetListDeserialization() {
        this(null);
    }

    public TweetListDeserialization(Class<?> vc) {
        super(vc);
    }

    @Override
    public Tweet deserialize(
        JsonParser jsonParser,
        DeserializationContext deserializationContext
    )
        throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String   createdAt = node.get("created_at").asText();
        Long     tweetId = node.get("id").asLong();
        String   text = node.get("text").asText();

        return new Tweet(createdAt, tweetId, text);
    }

}
