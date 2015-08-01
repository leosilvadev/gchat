package br.leosilvadev.gchat.mail.dto

import groovy.transform.EqualsAndHashCode;
import groovy.transform.Immutable

@Immutable
class Authentication {

	String username
	String password

	def authenticated(){
		new Authenticated(this, UUID.randomUUID().toString())
	}

	@Immutable
	static class Authenticated {
		@Delegate Authentication authentication
		String token

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
			+ ((authentication == null) ? 0 : authentication.hashCode());
			result = prime * result + ((token == null) ? 0 : token.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
			return true;
			if (obj == null)
			return false;
			if (getClass() != obj.getClass())
			return false;
			Authenticated other = (Authenticated) obj;
			if (authentication == null) {
				if (other.authentication != null)
				return false;
			} else if (!authentication.equals(other.authentication))
			return false;
			if (token == null) {
				if (other.token != null)
				return false;
			} else if (!token.equals(other.token))
			return false;
			return true;
		}
	}
}
