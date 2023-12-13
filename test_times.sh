#!/bin/bash

SUREFIRE_REPORT_DIR="target/surefire-reports"
README_FILE="README.md"
TMP_SORTED_FILE="sorted_class_names.txt"

# Check if the directory exists
if [ ! -d "$SUREFIRE_REPORT_DIR" ]; then
    echo "Surefire reports directory not found: $SUREFIRE_REPORT_DIR"
    exit 1
fi

# Clear existing README file
> "$README_FILE"

# Start the table in README.md
echo "| Year | Day | Class Name | Number of Tests | Number of Failures | Number of Skipped Tests | Total Runtime (s) |" >> "$README_FILE"
echo "|------|-----|-------------|------------------|---------------------|--------------------------|---------------------|" >> "$README_FILE"

# Collect all class names from XML files
class_names=$(find "$SUREFIRE_REPORT_DIR" -type f -name "*.xml" -exec awk -F'classname="' '/<testcase /{gsub(/".*/, "", $2); print $2}' {} \; | sort -u)

# Sort the class names by year and then day with single-digit numbers first
sorted_class_names=$(echo "$class_names" | awk -F'aoc.' '{year=substr($2, 2, 4); day=substr($2, 10, 2); printf "%s %02d %02d\n", $0, year, day}' | sort -k2,2n -k3,3n | awk '{print $1}' > "$TMP_SORTED_FILE")

# Echo the extracted information to README.md in table format
while IFS= read -r class_name; do
    # Extract year and day from the class name
    year=$(echo "$class_name" | awk -F'aoc.' '{print substr($2, 2, 4)}')
    day=$(echo "$class_name" | awk -F'day' '{print $2}' | awk -F'[^0-9]' '{print $1}')

    # Remove everything before the last '.'
    class_name=$(echo "$class_name" | sed 's/.*\.//')

    echo "| $year | $day | $class_name | $num_tests | $num_failures | $num_skipped | ${total_runtime}s |" >> "$README_FILE"
done < "$TMP_SORTED_FILE"

# Remove temporary sorted file
rm "$TMP_SORTED_FILE"
