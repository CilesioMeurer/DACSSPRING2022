package br.univille.apidacs2022.opentelemetry;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.jaegertracing.internal.samplers.RateLimitingSampler;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.Configuration.ReporterConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;





@Configuration
public class JaegerConfig {
    @Bean
    public JaegerTracer jaegerTracer(){
        // ATENÇÂO: NUNCA FAZER ISSO EM PRODUÇÃO
        // var sampler = new SamplerConfiguration()
        //     .withType(ConstSampler.TYPE)
        //     .withParam(1);
        var sampler = new SamplerConfiguration()
            .withType(RateLimitingSampler.TYPE)
            .withParam(10);
    var reporter = new ReporterConfiguration()
        .withLogSpans(true)
        .withSender(
            new SenderConfiguration()
                .withAgentHost("172.18.0.35")
        );

    return new io.jaegertracing
        .Configuration("dacsapi-cilesio")
        .withSampler(sampler)
        .withReporter(reporter)
        .getTracer();

    }

}
