package taxiguider;

import taxiguider.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당요청됨_(@Payload 택시할당요청됨 택시할당요청됨){

        if(택시할당요청됨.isMe()){
            System.out.println("##### listener  : " + 택시할당요청됨.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당취소됨_(@Payload 택시할당취소됨 택시할당취소됨){

        if(택시할당취소됨.isMe()){
            System.out.println("##### listener  : " + 택시할당취소됨.toJson());
        }
    }

}
