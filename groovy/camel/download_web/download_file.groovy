
@GrabResolver(name = "Apache Camel SNAPSHOTS", root = "https://repository.apache.org/content/repositories/snapshots/")
@Grab("org.apache.camel:camel-core:2.9.0-SNAPSHOT")
@Grab("org.apache.camel:camel-http:2.9.0-SNAPSHOT")
import org.apache.camel.Processor
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.builder.RouteBuilder

def dir = args[0]
def url = args[1]
def file = new File(url).name

ctx = new DefaultCamelContext()

ctx.addRoutes(new RouteBuilder() {
	void configure() {
		from("direct:start")
			.to(url)
			.to("file:${dir}?fileName=${file}")
			.end()
	}
})

ctx.start()

template = ctx.createProducerTemplate()
template.sendBody("direct:start", null)
