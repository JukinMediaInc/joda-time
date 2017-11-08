package jodatest

import grails.buildtestdata.mixin.Build
import org.joda.time.DateTimeZone
import spock.lang.Shared

@Build([City])
class DateTimeZoneScaffoldingSpec extends GebSpec {

	@Shared def london

	def setup() {
		City.withNewSession {
			london = City.build(name: "London", timeZone: DateTimeZone.forID("Europe/London"))
		}
	}

	def cleanup() {
		City.withNewSession {
			City.list()*.delete(flush: true)
		}
	}

	def "list"() {
		when:
		go "/city/index"

		then:
		$("tbody tr", 0).find("td", 0).text() == london.name
		$("tbody tr", 0).find("td", 1).text() == "$london.timeZone"
	}

	def "create"() {
		when:
		go "/city/create"
		$("form").name = "Vancouver"
		$("form").timeZone = "America/Vancouver"
		$("input.save").click()

		then:
		$(".message").text() ==~ /City \d+ created/

		and:
		def jerome = City.findByName("Vancouver")
		jerome.timeZone == DateTimeZone.forID("America/Vancouver")
	}

	def "show"() {
		when:
		go "/city/show/$london.id"

		then:
		$("li.fieldcontain", 0).find(".property-value").text() == london.name
		$("li.fieldcontain", 1).find(".property-value").text() == "Europe/London"
	}

	def "edit"() {
		when:
		go "/city/edit/$london.id"

		then:
		$("form").name == "London"
		$("form").timeZone == "Europe/London"
	}
}
