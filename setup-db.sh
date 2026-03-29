#!/bin/bash
# 313 Business Points Remittance Platform - PostgreSQL Setup Script
# Run this script using `sudo -u postgres ./setup-db.sh` or run the psql commands manually as a superuser.

echo "Setting up PostgreSQL for 313 Business Points..."

# Creating the user, database, and granting privileges
psql -c "CREATE USER apple WITH PASSWORD 'backend';"
psql -c "CREATE DATABASE remit313 OWNER apple;"
psql -c "GRANT ALL PRIVILEGES ON DATABASE remit313 TO apple;"
psql -d remit313 -c "GRANT ALL ON SCHEMA public TO apple;"

echo "Setup complete! Verify connection with:"
echo "psql -U apple -d remit313 -h localhost -W"
