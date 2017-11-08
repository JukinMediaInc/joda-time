package jodatest

import grails.buildtestdata.mixin.Build
import org.joda.time.Period
import spock.lang.Unroll

@Build([Marathon])
@Unroll
class DurationScaffoldingSpec extends GebSpec {

	def marathon1

	def setup() {
		Marathon.withNewSession {
			marathon1 = Marathon.build(runner: "Haile Gebrselassie", time: new Period(2, 3, 59, 0).toStandardDuration())
		}
	}

	def cleanup() {
		Marathon.withNewSession {
			Marathon.list()*.delete(flush: true)
		}
	}

	def "list"() {
		when:
		go "/marathon/index"

		then:
		$("tbody tr", 0).find("td", 0).text() == marathon1.runner
		$("tbody tr", 0).find("td", 1).text() == "2 hours, 3 minutes and 59 seconds"
	}

	def "create"() {
		when:
		go "/marathon/create"

		$("form").runner = "Glenn Saqui"
		$("form").time_hours = "2"
		$("form").time_minutes = "36"
		$("form").time_seconds = "51"
		$("input.save").click()

		then:
		$(".message").text() ==~ /Marathon \d+ created/

		and:
		def marathon2 = Marathon.findByRunner("Glenn Saqui")
		marathon2.time == new Period(2, 36, 51, 0).toStandardDuration()
	}

	def "show"() {
		when:
		go "/marathon/show/$marathon1.id"

		then:
		$("li.fieldcontain", 1).find(".property-value").text() == "2 hours, 3 minutes and 59 seconds"
	}

	def "edit"() {
		when:
		go "/marathon/edit/$marathon1.id"

		then:
		$("form").time_hours == "2"
		$("form").time_minutes == "3"
		$("form").time_seconds == "59"
	}

	def "list view is sorted after clicking on the column header #x times"() {
		given:
		Marathon.build(runner: "Glenn Saqui", time: new Period(2, 36, 51, 0).toStandardDuration())
		Marathon.build(runner: "Samuel Wanjiru", time: new Period(2, 5, 10, 0).toStandardDuration())

		when:
		go "/marathon/index"
		x.times {
			$("th a", text: "Time").click()
		}

		then:
		$("tbody tr")*.find("td", 0)*.text() == expected

		where:
		x | expected
		0 | ["Haile Gebrselassie", "Glenn Saqui", "Samuel Wanjiru"]
		1 | ["Haile Gebrselassie", "Samuel Wanjiru", "Glenn Saqui"]
		2 | ["Glenn Saqui", "Samuel Wanjiru", "Haile Gebrselassie"]
	}
}
