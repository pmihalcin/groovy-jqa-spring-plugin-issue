package mihalcin

import com.github.tomakehurst.wiremock.WireMockServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor
import static com.github.tomakehurst.wiremock.client.WireMock.post
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

/**
 * Created by Patrik.Mihalcin on 12.03.2018
 */
@Configuration
class WiremockLauncher {

    private static final int PORT = 9999

    @Bean
    WiremockRunner wiremockRunner() {
        new WiremockRunner()
    }

    class WiremockRunner {

        @PostConstruct
        void init() {
            def wireMockServer = new WireMockServer(options().port(PORT))
            wireMockServer.start()

            configureFor(PORT)
            stubFor(post('/bla')
                    .willReturn(aResponse().withBody("bla").withStatus(200)))
        }
    }
}
