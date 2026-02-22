# ADSync JSON Data Generation Tool

A Java-based tool for generating large-scale synthetic JSON test data for Active Directory Sync (ADSync) — including Users, Groups, Devices, and Organizational Units — distributed across multiple tenants with configurable scale sets.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Generated Entities](#generated-entities)
- [Configuration](#configuration)
- [Usage](#usage)

---

## Overview

This tool generates realistic synthetic Active Directory data in JSON format for load testing and integration testing. It reads tenant IDs from a CSV file and generates a configurable number of Users, Groups, Devices, and Organizational Units per tenant, organized into scale sets (A, B, C, D) based on `config.properties`.

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java | Core language |
| Maven | Build tool |
| Jackson | Java object to JSON serialization |
| Apache Commons IO | File utilities |

---

## Project Structure

```
adsync-json-data-generation-tool/
├── src/main/java/com/adsync/data/
│   ├── generators/
│   │   ├── UsersAndGroupsWrapper.java         # Generates Users & Groups JSON per tenant
│   │   ├── DevicesAndOrganizationsWrapper.java # Generates Devices & OrgUnits JSON per tenant
│   │   └── DataScale.java                     # Scale configuration (max counts, distribution)
│   ├── pojos/
│   │   ├── User.java                          # AD User entity (dn, email, sid, proxy_addresses, etc.)
│   │   ├── Group.java                         # AD Group entity (dn, email, members, sid, etc.)
│   │   ├── DeviceUnit.java                    # AD Device (name, dns_host_name, OS, sid, etc.)
│   │   ├── OrganizationalUnit.java            # AD OU (name, dn, child_devices, child_ous)
│   │   ├── UsersAndGroups.java                # Wrapper: origin + users[] + groups[]
│   │   └── DevicesAndOrganizations.java       # Wrapper: origin + devices[] + organizationalUnits[]
│   └── utilities/
│       ├── CsvParser.java                     # Reads tenant IDs from CSV
│       ├── RandomUtils.java                   # Random string and alphanumeric generators
│       ├── PropertyHelper.java                # Reads config.properties with DataScale parsing
│       └── CompressAndEncode.java             # DEFLATE compress + Base64 encode JSON output
├── config.properties                          # Scale set configuration
├── Runner.sh                                  # Shell script entry point
└── pom.xml
```

---

## Generated Entities

### User
Simulates an Active Directory user with fields:
`dn`, `email`, `external_id`, `name`, `given_name`, `surname`, `login`, `sid`, `principal_name`, `proxy_addresses`, `delegate_dns`, `exchange_login`, `primary_group_sid`

### Group
Simulates an AD distribution group with fields:
`name`, `dn`, `email`, `external_id`, `members[]`, `proxy_addresses[]`, `sid`

### Device Unit
Simulates a Windows 10 computer with fields:
`name`, `dn`, `external_id`, `domain`, `dns_host_name`, `operating_system`, `operating_system_sp`, `operating_system_version`, `sid`

### Organizational Unit
Simulates an AD OU with fields:
`name`, `dn`, `external_id`, `domain`, `child_ous[]`, `child_devices[]`

---

## Configuration

Edit `config.properties` to control data volumes per scale set:

```properties
# Maximum users and groups per run
max.user.onerun=500000
max.group.onerun=14000

# Scale set A (small tenants)
ds.max.user.a=2500
ds.max.group.a=80
ds.distribution.u.g.a.set.1=25,80

# Scale set B (medium tenants)
ds.max.user.b=27500
ds.max.group.b=701

# Scale set C (large tenants)
ds.max.user.c=50000
ds.max.group.c=1400

# Scale set D (enterprise tenants)
ds.max.user.d=500000
ds.max.group.d=14000

# Devices and Organizations
max.devices.onerun=25000
max.orgs.onerun=100
```

Provide a `tenantId.csv` file with one tenant ID per row (header row is skipped). Output JSON files are written to `data/json/<tenantId>/`.

---

## Usage

1. **Build the JAR**
   ```bash
   mvn clean install
   ```

2. **Run the generator**
   ```bash
   sh Runner.sh
   ```

   This executes `UsersAndGroupsWrapper` and writes JSON files to `data/json/` organized by tenant ID.

3. **Output format** — Files are named by entity count, e.g.:
   ```
   data/json/<tenantId>/usersAndGroups2500x80.json
   data/json/<tenantId>/devicesAndOrganization200x100.json
   ```
