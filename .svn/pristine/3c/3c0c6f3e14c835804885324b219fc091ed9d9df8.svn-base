package com.people2000.common.cache.redis;

import java.util.List;

public class HAConfig {

	public List<ServerProperties> groups;
	public int timeout = 3000;
	public boolean failover = true;
	public String password;

	public HAConfig() {
	}

	@Override
	public String toString() {
		return "HAConfig{" + "groups=" + groups + '}';
	}

	public static class ServerInfo {
		public String addr;
		public int port;

		public String generateKey() {
			return addr + "_" + port;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final ServerInfo other = (ServerInfo) obj;
			if ((this.addr == null) ? (other.addr != null) : !this.addr
					.equals(other.addr)) {
				return false;
			}
			if (this.port != other.port) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int hash = 3;
			hash = 41 * hash + addr.hashCode();
			hash = 41 * hash + this.port;
			return hash;
		}

		@Override
		public String toString() {
			return "addr=" + addr + ", port=" + port;
		}
	}

	public static class ServerProperties {
		public ServerInfo server;
		public int timeout;
		public String password;

		public ServerProperties() {
		}

		public ServerProperties(ServerInfo server, int timeout) {
			this(server, timeout, null);
		}

		public ServerProperties(ServerInfo server, int timeout, String password) {
			this.server = server;
			this.timeout = timeout;
			this.password = password;
		}

		public String generateKey() {
			return server.generateKey() + "_" + timeout;
		}

		@Override
		public String toString() {
			return "servers=" + server + ",timeout=" + timeout;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final ServerProperties other = (ServerProperties) obj;
			if (this.server == null || other.server == null
					|| !this.server.equals(other.server)) {
				return false;
			}
			if (this.timeout != other.timeout) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int hash = this.server.hashCode();
			hash = 41 * hash + this.timeout;
			return hash;
		}
	}
}
