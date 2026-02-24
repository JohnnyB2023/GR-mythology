Add-Type -AssemblyName System.IO.Compression.FileSystem
$zipPath = "c:\Android\PROJECTS\New_Mythology\Objects\bin\temp.ap_"
$zip = [System.IO.Compression.ZipFile]::OpenRead($zipPath)
Write-Host "=== All assets/ entries ==="
foreach ($entry in $zip.Entries) {
    if ($entry.FullName -like "assets/*") {
        Write-Host "$($entry.FullName)  ($($entry.Length) bytes)"
    }
}
$zip.Dispose()
