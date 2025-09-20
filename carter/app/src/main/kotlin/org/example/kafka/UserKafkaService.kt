import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserKafkaService(
    private val userRepository: UserRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @KafkaListener(topics = ["request-topic"], groupId = "kotlin-service")
    fun consume(message: String) {
        println("Got request: $message")

        val id = message.toLongOrNull()
        val user = id?.let { userRepository.findById(it).orElse(null) }

        val response = user?.name ?: "not_found"
        kafkaTemplate.send("response-topic", response)

        println("Sent response: $response")
    }
}
