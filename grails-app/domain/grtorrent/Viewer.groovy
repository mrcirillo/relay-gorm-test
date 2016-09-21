package grtorrent

import io.cirill.relay.annotation.RelayConnection
import io.cirill.relay.annotation.RelayField
import io.cirill.relay.annotation.RelayQuery
import io.cirill.relay.annotation.RelayType
import io.cirill.relay.dsl.GQLConnectionTypeSpec
import io.cirill.relay.dsl.GQLFieldSpec

/**
 * grtorrent
 * @author mcirillo
 */

@RelayType
class Viewer {

	static hasMany = [
	        comments: Comment
	]

	@RelayField
	String name

	@RelayQuery
	static viewerQuery = {
		GQLFieldSpec.field {
			name 'viewer'
			type {
				ref 'Viewer'
			}
			dataFetcher { env -> Viewer.first() }
		}
	}

	@RelayConnection(connectionFor = 'comments')
	static commentsConnection = {
		GQLConnectionTypeSpec.connectionType {
			name 'Comments'
			edgeType {
				ref 'Comment'
			}
		}
	}
}
